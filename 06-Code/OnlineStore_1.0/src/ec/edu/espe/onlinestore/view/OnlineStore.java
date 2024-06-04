
    package ec.edu.espe.onlinestore.view;

    import ec.ecu.espe.onlinestore.controller.StoreController;
    import ec.edu.espe.onlinestore.model.Store;

    /**
     *
     * @author Marco Chanataxi, Dev Dynasty, DCCO-ESPE
     */
    public class OnlineStore {
        public static void main(String[] args) {
            Store store = new Store();
            Menu menu = new Menu();
            StoreController controller = new StoreController(store, menu);
            controller.start();
        }

    }
