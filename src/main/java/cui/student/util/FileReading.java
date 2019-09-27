package cui.student.util;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class FileReading {
    public Set<String> getFileFields(File file) throws IOException {
        Set<String> fieldsSet=new HashSet<String>();
        FileReader fileReader=new FileReader(file);
        BufferedReader bufferedReader=new BufferedReader(fileReader);
        String line="";
        while ((line=bufferedReader.readLine())!=null){
            fieldsSet.add(line);
        }
        return fieldsSet;
    }
}
