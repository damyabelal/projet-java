package game;
import java.util.List;

public class ActionChooser<T> {
    public T choose(List<? extends T> list){
        if (list.isEmpty()){
            return null;
        }
        int choice= -1;
        while ((choice<0) || (choice>list.size())) {
            System.out.println("choose an action:");
            System.out.println("0- none");
            int index= 1;
            for (T element : list){
                System.out.println((index++) + "- "+ element);
            }
            System.out.println("->");
            try {
				choice = Input.readInt();
			} catch (java.io.IOException e) {
				System.out.println("Please, enter a number between 0 and " + (index-1));
			}
        }
        if (choice ==0){
            return null;
        }
        return list.get(choice-1);
    }
}
