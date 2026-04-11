package com.saavn.core

import com.saavn.commandmeta.CommandBase
import com.saavn.commandmeta.HelpCommand
import com.saavn.commands.info.InviteCommand
import com.saavn.commands.info.PingCommand
import com.saavn.commands.music.JoinCommand
import com.saavn.commands.music.PauseCommand
import com.saavn.commands.music.PlayCommand
import com.saavn.commands.music.QueueCommand
import com.saavn.commands.music.RepeatCommand
import com.saavn.commands.music.ResumeCommand
import com.saavn.commands.music.SkipCommand
import com.saavn.commands.music.StopCommand
import com.saavn.commands.music.VolumeCommand
import net.dv8tion.jda.api.JDA

class CommandManager(bot: SaavnDna, jda: JDA) {
    private val commandList: MutableList<CommandBase> = mutableListOf()
    private val commandLookup: MutableMap<String, CommandBase> = mutableMapOf()

    init {
        registerCommand(PingCommand(jda))
        registerCommand(InviteCommand(jda))
        registerCommand(JoinCommand(jda))
        registerCommand(PlayCommand(jda))
        registerCommand(StopCommand(jda))
        registerCommand(SkipCommand(jda))
        registerCommand(RepeatCommand(jda))
        registerCommand(PauseCommand(jda))
        registerCommand(ResumeCommand(jda))
        registerCommand(VolumeCommand(jda))
        registerCommand(QueueCommand(jda))

        registerCommand(HelpCommand(jda, commandList))
    }

    private fun registerCommand(command: CommandBase) {
        commandList.add(command)
        commandLookup[command.getName().lowercase()] = command
    }

    fun getCommand(search: String): CommandBase? {
        return commandLookup[search.lowercase()]
    }
}
