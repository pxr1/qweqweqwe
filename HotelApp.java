package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HotelApp {

    public static void main(String[] args) {
        RoomManager roomManager = new RoomManager(new ArrayList<>());
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Додати номер");
            System.out.println("2. Редагувати номер");
            System.out.println("3. Видалити номер");
            System.out.println("4. Сортувати номери за кількістю відвідувачів");
            System.out.println("5. Сортувати номери за вартістю");
            System.out.println("6. Пошук номера за ідентифікатором");
            System.out.println("7. Завантажити з текстового файлу");
            System.out.println("8. Зберегти в текстовий файл");
            System.out.println("9. Завантажити з Excel файлу");
            System.out.println("10. Зберегти в Excel файл");
            System.out.println("11. Вийти");
            System.out.print("Виберіть опцію: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Введіть номер кімнати: ");
                    int roomNumber = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Введіть клас кімнати (ECONOMY, STANDARD, LUXURY): ");
                    Room.RoomClass roomClass = Room.RoomClass.valueOf(scanner.nextLine().toUpperCase());
                    System.out.print("Введіть кількість осіб: ");
                    int numberOfPersons = scanner.nextInt();
                    System.out.print("Введіть вартість проживання за добу: ");
                    int pricePerNight = scanner.nextInt();
                    roomManager.addRoom(new Room(roomNumber, roomClass, numberOfPersons, pricePerNight));
                    break;
                case 2:
                    System.out.print("Введіть номер кімнати для редагування: ");
                    int editRoomNumber = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Введіть новий клас кімнати (ECONOMY, STANDARD, LUXURY): ");
                    Room.RoomClass newRoomClass = Room.RoomClass.valueOf(scanner.nextLine().toUpperCase());
                    System.out.print("Введіть нову кількість осіб: ");
                    int newNumberOfPersons = scanner.nextInt();
                    System.out.print("Введіть нову вартість проживання за добу: ");
                    int newPricePerNight = scanner.nextInt();
                    roomManager.editRoom(editRoomNumber, new Room(editRoomNumber, newRoomClass, newNumberOfPersons, newPricePerNight));
                    break;
                case 3:
                    System.out.print("Введіть номер кімнати для видалення: ");
                    int deleteRoomNumber = scanner.nextInt();
                    roomManager.deleteRoom(deleteRoomNumber);
                    break;
                case 4:
                    roomManager.sortByVisitors();
                    System.out.println("Номери відсортовані за кількістю відвідувачів.");
                    break;
                case 5:
                    roomManager.sortByPrice();
                    System.out.println("Номери відсортовані за вартістю.");
                    break;
                case 6:
                    System.out.print("Введіть номер кімнати для пошуку: ");
                    int searchRoomNumber = scanner.nextInt();
                    Room room = roomManager.findRoomById(searchRoomNumber);
                    if (room != null) {
                        System.out.println("Знайдено номер: " + room);
                    } else {
                        System.out.println("Номер не знайдено.");
                    }
                    break;
                case 7:
                    System.out.print("Введіть назву файлу: ");
                    String loadTextFileName = scanner.nextLine();
                    roomManager.loadFromTextFile(loadTextFileName);
                    System.out.println("Дані завантажено з текстового файлу.");
                    break;
                case 8:
                    System.out.print("Введіть назву файлу: ");
                    String saveTextFileName = scanner.nextLine();
                    roomManager.saveToTextFile(saveTextFileName);
                    System.out.println("Дані збережено в текстовий файл.");
                    break;
                case 9:
                    System.out.print("Введіть назву файлу: ");
                    String loadExcelFileName = scanner.nextLine();
                    List<Room> loadedRooms = ExcelUtil.loadFromExcel(loadExcelFileName);
                    roomManager.setRooms(loadedRooms);
                    System.out.println("Дані завантажено з Excel файлу.");
                    break;
                case 10:
                    System.out.print("Введіть назву файлу: ");
                    String saveExcelFileName = scanner.nextLine();
                    ExcelUtil.saveToExcel(saveExcelFileName, roomManager.getRooms());
                    System.out.println("Дані збережено в Excel файл.");
                    break;
                case 11:
                    System.out.println("Вихід...");
                    return;
                default:
                    System.out.println("Неправильний вибір. Спробуйте ще раз.");
            }
        }
    }
}