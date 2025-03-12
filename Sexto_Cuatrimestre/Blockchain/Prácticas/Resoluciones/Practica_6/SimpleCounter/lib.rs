#![cfg_attr(not(feature = "std"), no_std, no_main)]
#![cfg_attr(not(feature = "std"), no_std, no_main)]
#[ink::contract]
mod simple_counter {
    use ink::storage::Mapping;
    use interfaces::ICounter; 

    #[ink(storage)]
    pub struct SimpleCounter {
        count: i32,
        owner: AccountId,
        whitelist: Mapping<AccountId, bool>,
    }

    impl SimpleCounter {
        #[ink(constructor)]
        pub fn new() -> Self {
            let caller = Self::env().caller();
            let mut whitelist = Mapping::default(); // Default initialization
            whitelist.insert(&caller, &true);
            Self { count: 0, owner: caller, whitelist }
        }

        #[ink(message)]
        pub fn add_to_whitelist(&mut self, account: AccountId) {
            assert!(self.env().caller() == self.owner, "Solo el owner puede agregar a la whitelist");
            self.whitelist.insert(&account, &true);
        }

        pub fn is_on_whitelist(&self, account: &AccountId) -> bool {
            self.whitelist.get(account).unwrap_or(false)
        }
    }

    impl ICounter for SimpleCounter {
        #[ink(message)]
        fn increment(&mut self) {
            let caller = Self::env().caller();
            assert!(self.is_on_whitelist(&caller), "No estàs autorizado!");
            self.count += 1;
        }

        #[ink(message)]
        fn decrement(&mut self) {
            let caller = Self::env().caller();
            assert!(self.is_on_whitelist(&caller), "No estàs autorizado!");
            assert!(self.count > 0, "El contador ya està en 0");
            self.count -= 1;
        }

        #[ink(message)]
        fn get(&self) -> i32 {
            self.count
        }
    }
}

#[cfg(test)]
mod tests {
    use ink::primitives::AccountId;
    use super::*;
    use interfaces::ICounter; 

    #[ink::test]
    fn test_simple_counter() {
        let mut counter = simple_counter::SimpleCounter::new();
        assert_eq!(counter.get(), 0);
        counter.increment();
        assert_eq!(counter.get(), 1);
        counter.decrement();
        assert_eq!(counter.get(), 0);
    }

    #[ink::test]
    fn test_add_to_whitelist() {
        let mut counter = simple_counter::SimpleCounter::new();
        let new_user = AccountId::from([0x02; 32]);
        counter.add_to_whitelist(new_user);
        assert!(counter.is_on_whitelist(&new_user));
    }

    #[ink::test]
    #[should_panic(expected = "No estàs autorizado!")]
    fn test_increment_without_whitelist() {
        let mut counter = simple_counter::SimpleCounter::new();
        let unauthorized_user = AccountId::from([0x03; 32]);
        ink::env::test::set_caller::<ink::env::DefaultEnvironment>(unauthorized_user);
        counter.increment();
    }
}
