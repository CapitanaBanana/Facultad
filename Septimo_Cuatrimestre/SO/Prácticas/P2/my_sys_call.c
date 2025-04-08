#include <linux/kernel.h> 
#include <linux/syscalls.h> 
#include <linux/sched.h> 
#include <linux/uaccess.h> 
#include <linux/sched/signal.h> 
#include <linux/slab.h>  // Para kmalloc y kfree 
SYSCALL_DEFINE1(my_sys_call, int, arg) { 
printk(KERN_INFO "My syscall called with arg: %d\n", arg); 
return 0; 
} 
SYSCALL_DEFINE2(get_task_info, char __user *, buffer, size_t, length) { 
   struct task_struct *task; 
   char kbuffer[1024];  // Buffer en el espacio del kernel 
   int offset = 0; 
 
   for_each_process(task) { 
       offset += snprintf(kbuffer + offset, sizeof(kbuffer) - offset, 
       "PID: %d | Nombre: %s | Estado: %d \n",task->pid, task->comm, 
task_state_index(task)); 
       if (offset >= sizeof(kbuffer))  // Evita sobrepasar el tamaño del 
buffer 
           break; 
       
       printk(KERN_INFO "PID: %d | Nombre: %s\n", task->pid, task->comm);    
   } 
 
   // Copia la información al espacio de usuario 
   if (copy_to_user(buffer, kbuffer, min(length, (size_t)offset))) 
       return -EFAULT; 
 
   return min(length, (size_t)offset); 
} 
 
SYSCALL_DEFINE2(get_threads_info, char __user *, buffer, size_t, length) { 
   struct task_struct *task, *thread; 
   char *kbuffer; 
   int offset = 0; 
 
   // Asignar memoria dinámica para el buffer 
   kbuffer = kmalloc(2048, GFP_KERNEL); 
   if (!kbuffer) 
       return -ENOMEM; 
 
   for_each_process(task) { 
       offset += snprintf(kbuffer + offset, 2048 - offset, 
                          "Proceso: %s (PID: %d)\n", task->comm, task->pid); 
 
       for_each_thread(task, thread) { 
           offset += snprintf(kbuffer + offset, 2048 - offset,   "    ├── Hilo: %s (TID: %d)\n", thread->comm, 
thread->pid); 
           if (offset >= 2048) 
               break; 
       } 
 
       if (offset >= 2048) 
           break; 
   } 
 
   if (copy_to_user(buffer, kbuffer, min(length, (size_t)offset))) { 
       kfree(kbuffer); 
       return -EFAULT; 
   } 
 
   kfree(kbuffer); 
   return min(length, (size_t)offset); 
}
