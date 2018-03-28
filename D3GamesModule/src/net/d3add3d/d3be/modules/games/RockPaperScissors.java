package net.d3add3d.d3be.modules.games;

import net.d3add3d.d3be.modules.core.CoreModule;
import net.d3add3d.d3be.modules.core.ICommandExecutor;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.obj.Embed;
import sx.blah.discord.modules.IModule;

import java.awt.*;
import java.util.Random;

public class RockPaperScissors implements ICommandExecutor {

	private CoreModule core;
	private IModule owner;

	RockPaperScissors(CoreModule core, IModule owner) {
		this.core = core;
		this.owner = owner;
	}

	@Override
	public boolean execute(MessageReceivedEvent event) {
		Random rng = new Random();
		int botChoice = rng.nextInt(3);
		String msg = event.getMessage().getContent();
		String[] list = msg.split(" ");
		Embed.EmbedField embedField1;
		int userChoice = translateChoice(list[1]);
		if(userChoice != -1) {
			if(botChoice == userChoice) { //draw
				embedField1 = new Embed.EmbedField("Result", Character.toUpperCase(translateChoice(userChoice).charAt(0)) + translateChoice(userChoice).substring(1) + " do/does nothing to " + translateChoice(botChoice) + ", it's a draw!", true);
			} else {
				if(botChoice == 0 && userChoice == 1) { //bot rock, player paper; player wins
					embedField1 = new Embed.EmbedField("Result", Character.toUpperCase(translateChoice(userChoice).charAt(0)) + translateChoice(userChoice).substring(1) + " wraps " + translateChoice(botChoice) + ", you win!", true);
				} else if(botChoice == 0 && userChoice == 2) { //bot rock, player scissors; bot wins
					embedField1 = new Embed.EmbedField("Result", Character.toUpperCase(translateChoice(botChoice).charAt(0)) + translateChoice(botChoice).substring(1) + " breaks " + translateChoice(userChoice) + ", I win!", true);
				} else if(botChoice == 1 && userChoice == 0) { //bot paper, player rock; bot wins
					embedField1 = new Embed.EmbedField("Result", Character.toUpperCase(translateChoice(botChoice).charAt(0)) + translateChoice(botChoice).substring(1) + " wraps " + translateChoice(userChoice) + ", I win!", true);
				} else if(botChoice == 1 && userChoice == 2) { //bot paper, player scissors; player wins
					embedField1 = new Embed.EmbedField("Result", Character.toUpperCase(translateChoice(userChoice).charAt(0)) + translateChoice(userChoice).substring(1) + " cut " + translateChoice(botChoice) + ", you win!", true);
				} else if(botChoice == 2 && userChoice == 0) { //bot scissors, player rock; player wins
					embedField1 = new Embed.EmbedField("Result", Character.toUpperCase(translateChoice(userChoice).charAt(0)) + translateChoice(userChoice).substring(1) + " breaks " + translateChoice(botChoice) + ", you win!", true);
				} else if(botChoice == 2 && userChoice == 1) { //bot scissors, player paper; bot wins
					embedField1 = new Embed.EmbedField("Result", Character.toUpperCase(translateChoice(botChoice).charAt(0)) + translateChoice(botChoice).substring(1) + " cut " + translateChoice(userChoice) + ", I win!", true);
				} else {
					embedField1 = new Embed.EmbedField("Error", "Something went wrong, please contact the developer.", true);
					return core.getUtils().sendEmbed(event.getChannel(), "Rock Paper Scissors", owner.getName(), new Color(255,0,0), embedField1);
				}
			}
			return core.getUtils().sendEmbed(event.getChannel(), "Rock Paper Scissors", owner.getName(), core.botDefColor, embedField1);
		}
		return core.getUtils().sendEmbed(event.getChannel(), "Rock Paper Scissors", owner.getName(), core.botDefColor, new Embed.EmbedField("Warning", "Your input is invalid!", true));
	}

	private int translateChoice(String input) {
		if(input.equalsIgnoreCase("rock") || input.equalsIgnoreCase("r")) {
			return 0;
		} else if(input.equalsIgnoreCase("paper") || input.equalsIgnoreCase("p")) {
			return 1;
		} else if(input.equalsIgnoreCase("scissors") || input.equalsIgnoreCase("s")) {
			return 2;
		} else {
			return -1;
		}
	}

	private String translateChoice(int input) {
		switch(input) {
			case 0: return "rock";
			case 1: return "paper";
			case 2: return "scissors";
			default: return "Error, how did that even happen?!!";
		}
	}
}
