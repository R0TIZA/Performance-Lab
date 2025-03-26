import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class task2 {
    public static void main(String[] args) {
        String circlePath = args[0];
        String pointsPath = args[1];
        double[] circCenter = new double[2];
        double circRadius = 0;

        // Reading from file coordinates of circle center and circle radius
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(circlePath));){
            String line = bufferedReader.readLine();
            circCenter = Arrays.stream(line.split(" "))
                    .mapToDouble(Double::parseDouble)
                    .toArray();
            line = bufferedReader.readLine();
            circRadius = Double.parseDouble(line);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        //Reading from file array of points
        List<double[]> points = new ArrayList<>(){{
            try(BufferedReader bufferedReader = new BufferedReader(new FileReader(pointsPath))){
                String line;
                while ((line = bufferedReader.readLine()) != null){
                    add(Arrays.stream(line.split(" "))
                            .mapToDouble(Double::parseDouble)
                            .toArray());
                }
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }};

        //Calculating length between point and circle center and printing the result
        for(double[] point : points){
            double pointPowLength = Math.pow(point[0]-circCenter[0], 2)+Math.pow(point[1]-circCenter[1], 2);
            if(pointPowLength < Math.pow(circRadius, 2)) {
                System.out.println(1);
            }
            else if(pointPowLength > Math.pow(circRadius, 2)) {
                System.out.println(2);
            }
            else {
                System.out.println(0);
            }
        }
    }
}
