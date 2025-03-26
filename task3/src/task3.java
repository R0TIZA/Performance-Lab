import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Test;
import entity.Tests;
import entity.Value;
import entity.Values;

import java.io.*;
import java.util.HashMap;
import java.util.List;

public class task3 {
    private static final ObjectMapper mapper = new ObjectMapper();


    public static void main(String[] args) {
        //Reading tests.json and values.json files
        String valuesJson = readJson(args[0]);
        String testsJson = readJson(args[1]);

        //Saving path to report.json file
        String reportPath = args[2];

        try {
            //Deserializing json format to POJO
            Tests tests = mapper.readValue(testsJson, Tests.class);
            Values values = mapper.readValue(valuesJson, Values.class);

            //Editing values
            HashMap<Integer, String> valuesMap = new HashMap<>(){{
                for(Value value : values.getValues()) {
                    put(value.getId(), value.getValue());
                }
            }};
            setValues(tests.getTests(), valuesMap);

            //Writing json to a file
            writeJson(reportPath, mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tests));
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
    }

    private static String readJson(String path) {
        StringBuilder result = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return result.toString();
    }

    private static void writeJson(String path, String json) {
        File reportFile = new File(path);
        if (reportFile.exists()) {
            reportFile.delete();
        }
        try(FileWriter fileWriter = new FileWriter(reportFile)) {
            fileWriter.write(json);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void setValues(List<Test> tests, HashMap<Integer, String> valuesMap) {
        for (Test test : tests) {
            if(valuesMap.containsKey(test.getId())) {
                test.setValue(valuesMap.get(test.getId()));
            }
            if(test.getValues()!=null)
                setValues(test.getValues(), valuesMap);
        }
    }
}
