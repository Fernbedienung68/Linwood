package com.github.codedoctorde.linwood.commands.fun;

import com.github.codedoctorde.linwood.commands.Command;
import com.github.codedoctorde.linwood.commands.CommandManager;
import com.github.codedoctorde.linwood.entity.GuildEntity;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * @author CodeDoctorDE
 */
public class FunCommand extends CommandManager {
    @Override
    public Command[] commands() {
        return new Command[]{
                new WindowsCommand(),
                new DiceCommand()
        };
    }

    @Override
    public @NotNull Set<String> aliases(GuildEntity entity) {
        return new HashSet<>(Arrays.asList(
                "fun", "f", "funny"
        ));
    }

    @Override
    public @NotNull ResourceBundle getBundle(GuildEntity entity) {
        return ResourceBundle.getBundle("locale.commands.fun.Base", entity.getLocalization());
    }
}
