import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class General {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static Map<PersonsInfo, List<FinesInfo>> listFines = new HashMap<PersonsInfo, List<FinesInfo>>();

    public static void main(String[] args) {
        fines();
        showMenu();
    }

    public static void fines() {
        PersonsInfo ivanov = new PersonsInfo("M65498", Cities.BREST);
        List<FinesInfo> ivanovFines = new ArrayList<>();
        FinesInfo ivanovFineOne = new FinesInfo(TypeOfFines.AUTO, 12345L);
        FinesInfo ivanovFineTwo = new FinesInfo(TypeOfFines.INCOME, 98792L);
        ivanovFines.add(ivanovFineOne);
        ivanovFines.add(ivanovFineTwo);
        listFines.put(ivanov, ivanovFines);

        PersonsInfo petrov = new PersonsInfo("I40256", Cities.MINSK);
        List<FinesInfo> petrovFines = new ArrayList<>();
        FinesInfo petrovFineOne = new FinesInfo(TypeOfFines.AUTO, 12352L);
        FinesInfo petrovFineTwo = new FinesInfo(TypeOfFines.LAND, 97923L);
        petrovFines.add(petrovFineOne);
        petrovFines.add(petrovFineTwo);
        listFines.put(petrov, petrovFines);

        PersonsInfo sidorov = new PersonsInfo("P16953", Cities.MINSK);
        List<FinesInfo> sidorovFines = new ArrayList<>();
        FinesInfo sidorovFineOne = new FinesInfo(TypeOfFines.INCOME, 51668L);
        FinesInfo sidorovFineTwo = new FinesInfo(TypeOfFines.PROPERTY, 86615L);
        sidorovFines.add(sidorovFineOne);
        sidorovFines.add(sidorovFineTwo);
        listFines.put(sidorov, sidorovFines);
    }

    public static void showMenu() {
        while (true) {
            System.out.println("\nВведите действие, которое хотите реализовать: " +
                    "\n1 Полная распечатка базы данных." +
                    "\n2 Распечатка данных по конкретному коду." +
                    "\n3 Распечатка данных по конкретному типу штрафа." +
                    "\n4 Распечатка данных по конкретному городу." +
                    "\n5 Добавление нового человека с информацией о нем." +
                    "\n6 Добавление новых штрафов для уже существующей записи." +
                    "\n7 Удаление штрафа." +
                    "\n8 Замена информации о человеке и его штрафах." +
                    "\n9 Выход из программы");
            Integer operation = null;
            try {
                operation = Integer.parseInt(reader.readLine());
            } catch (Exception e1) {
                System.out.println("Номера действия с таким номером не существует!!!");
            }
            try {
                switch (operation) {
                    case 1:
                        printListFines();
                        break;
                    case 2:
                        printById();
                        break;
                    case 3:
                        printByTypeOfFines();
                        break;
                    case 4:
                        printByCity();
                        break;
                    case 5:
                        addPerson();
                        break;
                    case 6:
                        addFine();
                        break;
                    case 7:
                        deleteFine();
                        break;
                    case 8:
                        changeInfo();
                        break;
                    case 9:
                        System.exit(1);
                    default:
                        System.out.println("Неверный ввод номера действия!!!");
                }
                break;

            } catch (Exception e2) {
                System.out.println("Неверный ввод !!!");
                continue;
            }
        }
    }

    public static void printListFines() throws IOException {
        for (Map.Entry x : listFines.entrySet()) {
            System.out.println(x.getKey() + " " + x.getValue());
        }
    }

    public static void printById() throws IOException {
        System.out.println("Введите идентификационный номер налогоплательщика");
        String finedId = reader.readLine();
        try {
            listFines.forEach((k, v) -> {
                if (k.getId().equals(finedId)) {
                    System.out.println("Персональная информация о плательщике " + finedId + ": " + k);
                    System.out.print("Номера неуплаченных штрафов: ");
                    v.forEach(w -> System.out.print(w + " "));
                    System.out.println();
                }
            });
        } catch (Exception eId) {
            System.out.println("Неверный номер id!!!");
        }
    }

    public static void printByTypeOfFines() {
        try {
            System.out.println("Введите тип штрафа за неуплаченный налог (подоходный, на авто, на недвижимость, земельный)");
            String finedTypeOfFine = reader.readLine();
            System.out.println("Список плательщиков, у которых не упалачен штраф " + finedTypeOfFine + ":");
            listFines.forEach((k, v) -> {
                v.forEach(w -> {
                    if (w.getTypeOfFine().equals(TypeOfFines.AUTO) && finedTypeOfFine.equalsIgnoreCase("На авто")) {
                        System.out.println(k + " " + v);
                    } else if (w.getTypeOfFine().equals(TypeOfFines.INCOME) && finedTypeOfFine.equalsIgnoreCase("Подоходный")) {
                        System.out.println(k + " " + v);
                    } else if (w.getTypeOfFine().equals(TypeOfFines.PROPERTY) && finedTypeOfFine.equalsIgnoreCase("На недвижимость")) {
                        System.out.println(k + " " + v);
                    } else if (w.getTypeOfFine().equals(TypeOfFines.LAND) && finedTypeOfFine.equalsIgnoreCase("Земельный")) {
                        System.out.println(k + " " + v);
                    }
                });
            });
        } catch (Exception e3) {
            System.out.println("Неверный тип штрафа!!!");
        }
    }

    public static void printByCity() {
        try {
            System.out.println("Введите название города, в котором зарегистрирован плательщик (Брест, Витебск, Гомель, Гродно, Могилев, Минск)");
            String finedСity = reader.readLine();
            System.out.println("Список плательщиков, зарегистрированных в городе " + finedСity + ":");
            listFines.forEach((k, v) -> {
                if (k.getCity().equals(Cities.BREST) && finedСity.equalsIgnoreCase("Брест")) {
                    System.out.println(k + " " + v);
                } else if (k.getCity().equals(Cities.VITEBSK) && finedСity.equalsIgnoreCase("Витебск")) {
                    System.out.println(k + " " + v);
                } else if (k.getCity().equals(Cities.GOMEL) && finedСity.equalsIgnoreCase("Гомель")) {
                    System.out.println(k + " " + v);
                } else if (k.getCity().equals(Cities.GRODNO) && finedСity.equalsIgnoreCase("Гродно")) {
                    System.out.println(k + " " + v);
                } else if (k.getCity().equals(Cities.MOGILEV) && finedСity.equalsIgnoreCase("Могилев")) {
                    System.out.println(k + " " + v);
                } else if (k.getCity().equals(Cities.MINSK) && finedСity.equalsIgnoreCase("Минск")) {
                    System.out.println(k + " " + v);
                }
            });
        } catch (Exception e4) {
            System.out.println("Неверное название города!!!");
        }
    }

    public static void addPerson() throws IOException {
        try {
            System.out.println("Введите идентификационный номер НОВОГО налогоплательщика");
            String newId = reader.readLine();
            System.out.println("Введите город, в котором он зарегистрирован");
            String newCity = reader.readLine();

            PersonsInfo newPerson = null;
            if (newCity.equalsIgnoreCase("Брест")) {
                newPerson = new PersonsInfo(newId, Cities.BREST);
            } else if (newCity.equalsIgnoreCase("Витебск")) {
                newPerson = new PersonsInfo(newId, Cities.VITEBSK);
            } else if (newCity.equalsIgnoreCase("Гомель")) {
                newPerson = new PersonsInfo(newId, Cities.GOMEL);
            } else if (newCity.equalsIgnoreCase("Гродно")) {
                newPerson = new PersonsInfo(newId, Cities.GRODNO);
            } else if (newCity.equalsIgnoreCase("Могилев")) {
                newPerson = new PersonsInfo(newId, Cities.MOGILEV);
            } else if (newCity.equalsIgnoreCase("Минск")) {
                newPerson = new PersonsInfo(newId, Cities.MINSK);
            }

            System.out.println("Введите тип штрафа за неуплаченный налог (подоходный, на авто, на недвижимость, земельный)");
            String newTypeOfFine = reader.readLine();
            System.out.println("Введите номер штрафа");
            Long newNumb = Long.parseLong(reader.readLine());
            List<FinesInfo> newInfoFine = new ArrayList<>();
            FinesInfo newFine = null;

            if (newTypeOfFine.equalsIgnoreCase("Подоходный")) {
                newFine = new FinesInfo(TypeOfFines.INCOME, newNumb);
                newInfoFine.add(newFine);
            } else if (newTypeOfFine.equalsIgnoreCase("На авто")) {
                newFine = new FinesInfo(TypeOfFines.AUTO, newNumb);
                newInfoFine.add(newFine);
            } else if (newTypeOfFine.equalsIgnoreCase("На недвижимость")) {
                newFine = new FinesInfo(TypeOfFines.PROPERTY, newNumb);
                newInfoFine.add(newFine);
            } else if (newTypeOfFine.equalsIgnoreCase("Земельный")) {
                newFine = new FinesInfo(TypeOfFines.LAND, newNumb);
                newInfoFine.add(newFine);
            }
            listFines.putIfAbsent(newPerson, newInfoFine);

        } catch (Exception e5) {
            System.out.println("Неверный ввод!!!");
        }
    }

    public static void addFine() {
        try {
            System.out.println("Введите идентификационный номер налогоплательщика, которому необходимо ДОБАВИТЬ штраф");
            String idNewFine = reader.readLine();
            System.out.println("Введите тип нового штрафа за неуплаченный налог (подоходный, на авто, на недвижимость, земельный)");
            String typeNewFine = reader.readLine();
            System.out.println("Введите номер нового штрафа");
            Long numbNewFine = Long.parseLong(reader.readLine());
            listFines.forEach((k, v) -> {
                if (k.getId().equals(idNewFine)) {
                    if (typeNewFine.equalsIgnoreCase("На авто")) {
                        v.add(new FinesInfo(TypeOfFines.AUTO, numbNewFine));
                    } else if (typeNewFine.equalsIgnoreCase("Подоходный")) {
                        v.add(new FinesInfo(TypeOfFines.INCOME, numbNewFine));
                    } else if (typeNewFine.equalsIgnoreCase("На недвижимость")) {
                        v.add(new FinesInfo(TypeOfFines.PROPERTY, numbNewFine));
                    } else if (typeNewFine.equalsIgnoreCase("Земельный")) {
                        v.add(new FinesInfo(TypeOfFines.LAND, numbNewFine));
                    }
                }
            });
        } catch (Exception e6) {
            System.out.println("Неверный ввод данных!!!");
        }
    }

    public static void deleteFine() {
        try {
            System.out.println("Введите идентификационный номер налогоплательщика, которому необходимо УДАЛИТЬ штраф");
            String idDeleteFine = reader.readLine();
            System.out.println("Введите номер штрафа");
            Long deletedFine = Long.parseLong(reader.readLine());
            listFines.forEach((k, v) -> {
                if (k.getId().equals(idDeleteFine)) {
                    v.forEach(w -> {
                        if (w.getNumberOfFine().equals(deletedFine)) {
                            listFines.get(k).remove(w);
                        }
                    });
                }
            });
        } catch (Exception e7) {
            System.out.println("Неверный ввод данных!!!");
        }
    }

    public static void changeInfo() {
        System.out.println("Введите действие, которое хотите реализовать: " +
                "\n1 Изменить город регистрации плательщика." +
                "\n2 Изменить тип штрафа." +
                "\n3 Изменить номер штрафа.");
        Integer operation = null;
        try {
            operation = Integer.parseInt(reader.readLine());
        } catch (Exception e1) {
            System.out.println("Номера действия с таким номером не существует!!!");
        }
        try {
            switch (operation) {
                case 1:
                    changeCity();
                    showMenu();
                    break;
                case 2:
                    changeTypeOfFines();
                    showMenu();
                    break;
                case 3:
                    changeNumbOfFines();
                    showMenu();
                    break;
                default:
                    System.out.println("Неверный ввод номера действия!!!");
            }
        } catch (Exception e8) {
            System.out.println("Неверный ввод !!!");
        }

    }

    public static void changeCity() {
        try {
            System.out.println("Введите id плательщика, информацию о котором хотите изменить");
            String changedId = reader.readLine();
            System.out.println("Введите город, на который хотите изменить");
            String changedCity = reader.readLine();
            listFines.forEach((k, v) -> {
                if (k.getId().equals(changedId)) {
                    if (changedCity.equalsIgnoreCase("Брест")) {
                        k.setCity(Cities.BREST);
                    } else if (changedCity.equalsIgnoreCase("Витебск")) {
                        k.setCity(Cities.VITEBSK);
                    } else if (changedCity.equalsIgnoreCase("Гомель")) {
                        k.setCity(Cities.GOMEL);
                    } else if (changedCity.equalsIgnoreCase("Гродно")) {
                        k.setCity(Cities.GRODNO);
                    } else if (changedCity.equalsIgnoreCase("Могилев")) {
                        k.setCity(Cities.MOGILEV);
                    } else if (changedCity.equalsIgnoreCase("Минск")) {
                        k.setCity(Cities.MINSK);
                    }
                }
            });
        } catch (Exception e) {
            System.out.println("Неверный ввод города!!!");
        }
    }

    public static void changeTypeOfFines() throws IOException {
        System.out.println("Введите id плательщика, информацию о котором хотите изменить");
        String changedId = reader.readLine();
        System.out.println("Введите тип штрафа, который хотите изменить");
        String presentType = reader.readLine();
        System.out.println("Введите тип штрафа, НА КОТОРЫЙ хотите изменить");
        String changedType = reader.readLine();
        listFines.forEach((k, v) -> {
            v.forEach(x -> {
                if (presentType.equalsIgnoreCase("Подоходный")) {
                    if (changedType.equalsIgnoreCase("На авто")) {
                        x.setTypeOfFine(TypeOfFines.AUTO);
                    } else if (changedType.equalsIgnoreCase("На недвижимость")) {
                        x.setTypeOfFine(TypeOfFines.PROPERTY);
                    } else if (changedType.equalsIgnoreCase("Земельный")) {
                        x.setTypeOfFine(TypeOfFines.LAND);
                    }
                } else if (presentType.equalsIgnoreCase("На авто")) {
                    if (changedType.equalsIgnoreCase("Подоходный")) {
                        x.setTypeOfFine(TypeOfFines.INCOME);
                    } else if (changedType.equalsIgnoreCase("На недвижимость")) {
                        x.setTypeOfFine(TypeOfFines.PROPERTY);
                    } else if (changedType.equalsIgnoreCase("Земельный")) {
                        x.setTypeOfFine(TypeOfFines.LAND);
                    }
                } else if (presentType.equalsIgnoreCase("На недвижимость")) {
                    if (changedType.equalsIgnoreCase("Подоходный")) {
                        x.setTypeOfFine(TypeOfFines.INCOME);
                    } else if (changedType.equalsIgnoreCase("На авто")) {
                        x.setTypeOfFine(TypeOfFines.AUTO);
                    } else if (changedType.equalsIgnoreCase("Земельный")) {
                        x.setTypeOfFine(TypeOfFines.LAND);
                    }
                } else if (presentType.equalsIgnoreCase("Земельный")) {
                    if (changedType.equalsIgnoreCase("Подоходный")) {
                        x.setTypeOfFine(TypeOfFines.INCOME);
                    } else if (changedType.equalsIgnoreCase("На недвижимость")) {
                        x.setTypeOfFine(TypeOfFines.PROPERTY);
                    } else if (changedType.equalsIgnoreCase("На авто")) {
                        x.setTypeOfFine(TypeOfFines.AUTO);
                    }
                }
            });
        });
    }

    public static void changeNumbOfFines() throws IOException {
        System.out.println("Введите id плательщика, информацию о котором хотите изменить");
        String changedId = reader.readLine();
        System.out.println("Введите номер штрафа, который хотите изменить");
        Long presentNumb = Long.parseLong(reader.readLine());
        System.out.println("Введите номер штрафа, НА КОТОРЫЙ хотите изменить");
        Long changedNumb = Long.parseLong(reader.readLine());
        listFines.forEach((k, v) -> {
            v.forEach(x -> {
                if (x.getNumberOfFine().equals(presentNumb)) {
                    x.setNumberOfFine(changedNumb);
                }
            });
        });
    }
}

