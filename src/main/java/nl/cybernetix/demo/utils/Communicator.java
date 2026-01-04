package nl.cybernetix.demo.utils;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Communicator {
    public boolean askYesNoQuestion(String prompt) {
        System.out.println(prompt);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim().toLowerCase().contains("y");
    }
}
