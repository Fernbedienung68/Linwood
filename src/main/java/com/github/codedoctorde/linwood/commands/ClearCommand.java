package com.github.codedoctorde.linwood.commands;

import com.github.codedoctorde.linwood.entity.GuildEntity;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import org.hibernate.Session;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * @author CodeDoctorDE
 */
public class ClearCommand implements Command {
    @Override
    public boolean onCommand(Session session, Message message, GuildEntity entity, String label, String[] args) {
        if(args.length != 1)
            return false;
        int count;
        var bundle = getBundle(entity);
        try{
            count = Integer.parseInt(args[0]);
        }catch(Exception ignored){
            message.getChannel().sendMessage(bundle.getString("Invalid")).queue();
            return true;
        }
        if(count <= 0 || count > 100)
            message.getChannel().sendMessage(bundle.getString("Between")).queue();
        message.getChannel().getHistory().retrievePast(count).queue(messages -> {
            messages.forEach(deleteMessage -> deleteMessage.delete().queue());
            message.getChannel().sendMessage(MessageFormat.format(bundle.getString("Success"), messages.size())).queue();
        });
        return true;
    }

    @Override
    public boolean hasPermission(Member member, GuildEntity entity, Session session) {
        return member.hasPermission(Permission.MANAGE_CHANNEL);
    }

    @Override
    public @NotNull Set<String> aliases(GuildEntity entity) {
        return new HashSet<>(Arrays.asList(
                "clear", "c", "clearchat","clear-chat"
        ));
    }

    @Override
    public @NotNull ResourceBundle getBundle(GuildEntity entity) {
        return ResourceBundle.getBundle("locale.commands.Clear", entity.getLocalization());
    }
}
