package com.mjc.school;


import com.mjc.school.controller.NewsController;
import com.mjc.school.controller.impl.NewsControllerImpl;
import com.mjc.school.dto.NewsDto;
import com.mjc.school.exception.NotExistThisId;
import com.mjc.school.exception.NotNewDataToUpdate;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NotExistThisId, NotNewDataToUpdate, IOException {
        Scanner sc = new Scanner(System.in);
        NewsController<NewsDto> controller = new NewsControllerImpl();
        boolean status = true;
        while (status) {
            System.out.println("click for performing \n" +
                    "1 -> Create News\n" +
                    "2 -> All News\n" +
                    "3 -> Get News by id\n" +
                    "4 -> Update news by id\n" +
                    "5 -> Delete news by id\n" +
                    "6 -> Exit");
            int numOfFunction = sc.nextInt();
            switch (numOfFunction) {
                case 1 -> {
                    sc.nextLine();
                    System.out.println("Enter news title");
                    String title = sc.nextLine();
                    System.out.println("Enter news content");
                    String content = sc.nextLine();
                    System.out.println("Enter news authorId");
                    String authorId = sc.nextLine();
                    NewsDto newsDTO = controller.createNews(title);
                    newsDTO.setContent(content);
                    newsDTO.setAuthorId(Long.parseLong(authorId));
                    System.out.println(newsDTO);
                }
                case 2 -> {
                    System.out.print("ALL NEWS ");
                    System.out.println(controller.getAllNews());
                }
                case 3 -> {
                    System.out.println("Enter ID: ");
                    sc.nextLine();
                    String id = sc.nextLine();
                    System.out.println(controller.getNewsById(id));

                }
                case 4 -> {
                    sc.nextLine();
                    System.out.print("Enter News ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter updated title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter updated content: ");
                    String content = sc.nextLine();
                    System.out.print("Enter updated author ID: ");
                    String authorID = sc.nextLine();
                    NewsDto newsDTO = new NewsDto(title, content, Long.getLong(authorID));
                    controller.updateNews(id.toString(), title, content, authorID.toString());
                }
                case 5 -> {
                    sc.nextLine();
                    System.out.print("Enter Deleted News ID: ");
                    String id = sc.nextLine();
                    controller.deleteNews(id);
                }
                case 6 -> {
                    status = false;
                }
                default ->
                        System.out.println("finished .... :)");
            }
        }
        sc.close();
    }

 }

