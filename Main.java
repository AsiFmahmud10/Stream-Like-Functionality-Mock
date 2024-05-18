import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;



public class Main {
    public static void main(String[] args) {
        CustomList<Integer> list = new CustomArrayList<>(List.of(1,2,3,4,5));
        list.getStream().filter(n -> n%2 == 0).map(n-> n*10).forEach((n)->System.out.println(n));
    }

}
class Stream<T>{
    private CustomList<T> currentList ;
    private CustomList<T> newList = new CustomArrayList<>();

    public Stream(CustomList<T> list){
        this.currentList = list;
    }
    public void forEach(Consumer<T> consumer){
        for( T item : currentList ){
             consumer.accept(item);
        }
    }

    public Stream<T> map(Function<T,T> f){
        for( T item : currentList ){
            T newItem = f.apply(item);
            newList.add(newItem);
        }
           return new Stream<T>(newList);
    }


    public Stream<T> filter(Predicate<T> predicate){
        for( T item : currentList){
            if (predicate.test(item) == true){
                newList.add(item);
            }
        }
        return new Stream<T>(newList);
    }

    @Override
    public String toString() {
        return currentList.toString();
    }
}

interface CustomList<T> extends List<T>{
    Stream<T>  getStream();
}
class CustomArrayList<T> extends ArrayList<T> implements CustomList<T> {

    public CustomArrayList(){
      super();
    }

    public CustomArrayList(List<T> list){
        super(list);
    }

    @Override
    public Stream<T> getStream() {
        return new Stream<T>(this);
    }
}
