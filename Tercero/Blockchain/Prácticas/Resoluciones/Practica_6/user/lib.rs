#![cfg_attr(not(feature = "std"), no_std, no_main)]


#[ink::contract]
mod user {
    use Interfaces::ICounter;

    #[ink(storage)]
    pub struct User {
        owner: AccountId,
        counter: ink::contract_ref!(ICounter),
    }

    impl User {
        #[ink(constructor)]
        pub fn new(counter: AccountId) -> Self {
            Self { owner: Self::env().caller(), counter: counter.into() }
        }

        #[ink(message)]
        pub fn increment(&mut self) {
            self.counter.increment();
        }

        #[ink(message)]
        pub fn decrement(&mut self) {
            self.counter.decrement();
        }

        #[ink(message)]
        pub fn get(&self) -> i32 {
            self.counter.get()
        }
    }
}
