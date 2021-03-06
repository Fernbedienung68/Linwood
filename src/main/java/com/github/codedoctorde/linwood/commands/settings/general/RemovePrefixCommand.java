package com.github.codedoctorde.linwood.commands.settings.general;

import com.github.codedoctorde.linwood.commands.Command;
import com.github.codedoctorde.linwood.entity.GuildEntity;
import net.dv8tion.jda.api.entities.Message;
import org.hibernate.Session;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class RemovePrefixCommand implements Command {
    @Override
    public boolean onCommand(Session session, Message message, GuildEntity entity, String label, String[] args) {
        if(args.length != 1)
        return false;
        var bundle = getBundle(entity);
        var string = String.join(" ", args);
        if(!entity.getPrefixes().remove(string)){
            message.getChannel().sendMessage(bundle.getString("Invalid")).queue();
            return true;
        }
        entity.save(session);
        message.getChannel().sendMessage(MessageFormat.format(bundle.getString("Success"), args[0])).queue();
        return true;
    }

    @Override
    public @NotNull Set<String> aliases(GuildEntity entity) {
        return new HashSet<>(Arrays.asList(
                "removeprefix",
                "removepre-fix",
                "remove-prefix",
                "remove-pre-fix"
        ));
    }

    @Override
    public @NotNull ResourceBundle getBundle(GuildEntity entity) {
        return ResourceBundle.getBundle("locale.commands.settings.general.RemovePrefix", entity.getLocalization());
    }
}
