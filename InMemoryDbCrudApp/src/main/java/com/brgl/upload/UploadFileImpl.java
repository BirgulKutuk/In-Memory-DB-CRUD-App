package com.brgl.upload;

import com.brgl.dao.ExerciseDAOBean;
import com.brgl.dto.ExerciseDTOBean;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UploadFileImpl implements UploadFile {

    private static final String filePath = "src/main/java/com/brgl/sampledata/";
    private static final String fileName = "exercise.csv";
    private static final String SPLIT_BY = ",";

    public void uploadExerciseFile() {

        List<ExerciseDTOBean> data = readFile(filePath + fileName);
        createDB();
        uploadFileToDB(data);
    }

    private List<ExerciseDTOBean> readFile(String file) {

        String line = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Path pathToFile = Paths.get(file);
        ExerciseDTOBean dtoBean = new ExerciseDTOBean();
        List<ExerciseDTOBean> dtoBeanArrayList = new ArrayList<>();

        try {
            BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8);

            if (!checkFistLine(br.readLine())) {
                System.out.println("column names are not correct!");
                return null;
            }

            while ((line = br.readLine()) != null)
            {
                String[] row = line.replaceAll("\"","").split(SPLIT_BY, -1);
                dtoBean = new ExerciseDTOBean();
                dtoBean.setSource(row[0]);
                dtoBean.setCodeListCode(row[1]);
                dtoBean.setCode(row[2]);
                dtoBean.setDisplayValue(row[3]);
                dtoBean.setLongDescription(row[4]);
                dtoBean.setFromDate(row[5] != "" ? simpleDateFormat.parse(row[5]) : null);
                dtoBean.setToDate(row[6] != "" ? simpleDateFormat.parse(row[6]) : null);
                dtoBean.setSortingPriority(row[7] != "" ? row[7] : "");
                dtoBeanArrayList.add(dtoBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dtoBeanArrayList;
    }

    private boolean checkFistLine(String firstLine) {

        boolean isFirstLineValid = false;
        firstLine = firstLine.replaceAll("\"","");
        String[] firstRow = firstLine.split(SPLIT_BY);

        try {
            if (ExerciseDAOBean.source.equalsIgnoreCase(firstRow[0])
                    && ExerciseDAOBean.codeListCode.equalsIgnoreCase(firstRow[1])
                    && ExerciseDAOBean.code.equalsIgnoreCase(firstRow[2])
                    && ExerciseDAOBean.displayValue.equalsIgnoreCase(firstRow[3])
                    && ExerciseDAOBean.longDescription.equalsIgnoreCase(firstRow[4])
                    && ExerciseDAOBean.fromDate.equalsIgnoreCase(firstRow[5])
                    && ExerciseDAOBean.toDate.equalsIgnoreCase(firstRow[6])
                    && ExerciseDAOBean.sortingPriority.equalsIgnoreCase(firstRow[7])) {
                isFirstLineValid = true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return isFirstLineValid;
    }

    private void createDB() {
        // TODO
    }

    private void uploadFileToDB(List<ExerciseDTOBean> data) {
        // TODO
    }

}
