package net.ibxnjadev.ucn.parallel.helper.bot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;
import net.ibxnjadev.ucn.parallel.helper.ParallelClass;

import java.util.Set;

public class CommandListenerBot extends ListenerAdapter {

    private static final String PREFIX = ":";
    private static final String SPACE = " ";

    private final SqlSearchHandler sqlSearchHandler;

    public CommandListenerBot(SqlSearchHandler sqlSearchHandler) {
        this.sqlSearchHandler = sqlSearchHandler;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message message = event.getMessage();
        String content = message.getContentRaw();

        if (content.startsWith(PREFIX)) {
            String[] args = content.split(SPACE);

            if (content.startsWith(PREFIX + "buscar-por-asignatura")) {
                String name = args[1];

                Set<ParallelClass> parallelClasses =
                        sqlSearchHandler.getCompoundParallelByName(name);

                EmbedBuilder embedBuilder = new EmbedBuilder();

                for (ParallelClass parallelClass : parallelClasses) {
                    embedBuilder.addField("> ", convert(parallelClass), true);
                }

                MessageEmbed messageEmbed = embedBuilder.build();

                event.getChannel().sendMessageEmbeds(messageEmbed)
                        .complete();


            }

        }

    }

    public String convert(ParallelClass parallelClass) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Semestre: ")
                .append(parallelClass.getSemester())
                .append(" Id: ")
                .append(parallelClass.getIdentifier())
                .append(" Name: ")
                .append(parallelClass.getName())
                .append(" NRC: ")
                .append(parallelClass.getIdentifier())
                .append(" Sección: ")
                .append(parallelClass.getSection())
                .append(" Cupos: ")
                .append(parallelClass.getMembersLimit())
                .append(" Personas unidas: ")
                .append(parallelClass.getMembersJoined())
                .append(" Cupos disponibles: ")
                .append(parallelClass.getMembersAvailable())
                .append(" Creditos: ")
                .append(parallelClass.getCredits())
                .append("Tipo: ")
                .append(parallelClass.getType().toString())
                .append(" Apellido de profe ")
                .append(parallelClass.getLastNameTeacher())
                .append(" Nombre de profe")
                .append(parallelClass.getName());

        return stringBuilder.toString();
    }

}
