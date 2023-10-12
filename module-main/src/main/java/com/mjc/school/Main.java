package com.mjc.school;


import com.mjc.school.controller.NewsController;
import com.mjc.school.controller.NewsControllerImpl;
import com.mjc.school.dto.NewsDTO;
import com.mjc.school.exception.NotExistThisId;
import com.mjc.school.exception.NotNewDataToUpdate;
import com.mjc.school.repository.model.Author;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.NewsServiceImpl;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NotExistThisId, NotNewDataToUpdate {
        Scanner sc = new Scanner(System.in);
        NewsController<NewsDTO> controller = new NewsControllerImpl();
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
                    System.out.println("Enter news title");
                    String title = sc.nextLine();
                    System.out.println("Enter news content");
                    String content = sc.nextLine();
                    System.out.println("Enter news authorId");
                    String authorId = sc.nextLine();
                    NewsDTO newsDTO = controller.createNews(title, content, authorId);
                    System.out.println(newsDTO);
                }
                case 2 -> {
                    System.out.print("ALL NEWS ");
                    System.out.println(controller.getAllNews());
                }
                case 3 -> {
                    System.out.println("Enter author ID: ");
                    Long authorID = sc.nextLong();

                }
                case 4 -> {
                    System.out.print("Enter News ID: ");
                    Long id = sc.nextLong();
                    System.out.print("Enter updated title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter updated content: ");
                    String content = sc.nextLine();
                    System.out.print("Enter updated author ID: ");
                    Long authorID = sc.nextLong();
                    NewsDTO newsDTO = new NewsDTO(title, content, authorID);
                    controller.updateNews(id.toString(), title, content, authorID.toString());
                }
                case 5 -> {
                    System.out.print("Enter Deleted News ID: ");
                    Long id = sc.nextLong();
                    controller.deleteNews(id.toString());
                }
                case 6 -> {
                    status = false;
                }
                default ->
                        System.out.println("Are you crazy.... :)");
            }
        }
        sc.close();
    }

 }

