#![cfg_attr(not(feature = "std"), no_std, no_main)]
#[ink::trait_definition]
pub trait ICounter {
    #[ink(message)]
    fn increment(&mut self);
    
    #[ink(message)]
    fn decrement(&mut self);
    
    #[ink(message)]
    fn get(&self) -> i32;
}
