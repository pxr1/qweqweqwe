package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {

    public static void saveToExcel(String fileName, List<Room> rooms) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Rooms");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Room Number");
            headerRow.createCell(1).setCellValue("Room Class");
            headerRow.createCell(2).setCellValue("Number of Persons");
            headerRow.createCell(3).setCellValue("Price Per Night");

            int rowCount = 1;
            for (Room room : rooms) {
                Row row = sheet.createRow(rowCount++);
                writeRoom(room, row);
            }

            try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
                workbook.write(outputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeRoom(Room room, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue(room.getRoomNumber());

        cell = row.createCell(1);
        cell.setCellValue(room.getRoomClass().name());

        cell = row.createCell(2);
        cell.setCellValue(room.getNumberOfPersons());

        cell = row.createCell(3);
        cell.setCellValue(room.getPricePerNight());
    }

    public static List<Room> loadFromExcel(String fileName) {
        List<Room> rooms = new ArrayList<>();

        try (FileInputStream inputStream = new FileInputStream(fileName);
             Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;
                }

                int roomNumber = (int) row.getCell(0).getNumericCellValue();
                Room.RoomClass roomClass = Room.RoomClass.valueOf(row.getCell(1).getStringCellValue());
                int numberOfPersons = (int) row.getCell(2).getNumericCellValue();
                int pricePerNight = (int) row.getCell(3).getNumericCellValue();

                rooms.add(new Room(roomNumber, roomClass, numberOfPersons, pricePerNight));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rooms;
    }

}

