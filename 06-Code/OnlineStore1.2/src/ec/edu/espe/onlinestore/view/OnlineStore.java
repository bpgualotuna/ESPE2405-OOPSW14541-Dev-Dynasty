
    package ec.edu.espe.onlinestore.view;

    import ec.ecu.espe.onlinestore.controller.StoreController;
    import ec.edu.espe.onlinestore.model.Store;
import java.io.IOException;

    /**
     *
     * @author Marco Chanataxi, Dev Dynasty, DCCO-ESPE
     */
    public class OnlineStore {
        public static void main(String[] args) throws IOException {
        Store store = new Store();
        Menu menu;
        
        StoreController controller = new StoreController(store, new Menu());
       // controller.initializeStore();
        
        controller.start();
        
        
        
    }

    }
