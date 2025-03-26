import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class task4 {
    public static void main(String[] args) {
        String arrPath = args[0];
        List<Integer> numArray = new ArrayList<>(){{
            try(BufferedReader br = new BufferedReader(new FileReader(arrPath))){
                String line;
                while((line = br.readLine()) != null){
                    add(Integer.parseInt(line));
                }
            }catch (FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
        }};
        numArray.sort(Integer::compareTo);
        int sum = 0;
        int median = numArray.get(numArray.size()/2);
        for(int num : numArray){
            sum += Math.abs(num-median);
        }
        System.out.println(sum);
    }
}
