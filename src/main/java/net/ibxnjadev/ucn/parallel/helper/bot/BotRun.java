package net.ibxnjadev.ucn.parallel.helper.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.ibxnjadev.ucn.parallel.helper.SqlConnection;

public class BotRun {

    private static final String TOKEN = "";

    public static void main(String[] args) throws InterruptedException {

        SqlConnection sqlConnection = new SqlConnection(
                "localhost", 3306, "root", ""
        );

        sqlConnection.connect();

        SqlSearchHandler sqlSearchHandler = new SqlSearchHandler(sqlConnection.get());

        JDA jda = JDABuilder.createDefault("token")
                .addEventListeners(new CommandListenerBot(sqlSearchHandler))
                .build();

        jda.awaitReady();

    }

}
