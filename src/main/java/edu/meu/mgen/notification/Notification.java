package edu.meu.mgen.notification;

import edu.meu.mgen.user.User;

public class Notification {

    public void sendProgressNotification(User user) {
        System.out.println("Keep up the great work! You are making progress toward your goals.");
    }

    public void sendMotivationNotification(User user) {
        System.out.println("Don't give up! You're closer to your goal than you think.");
    }
}

