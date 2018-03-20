package net.d3add3d.d3be.modules.games;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;

/**
 * Project D3BotEvolved
 * Package net.d3add3d.d3be.modules.games
 * Created by D3add3d on 22. 1. 2018.
 * <p>
 * Copyright (C) 2018 D3add3d
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
public class GamesListener {

	private GamesModule gamesModule;

	GamesListener(GamesModule gamesModule) {
		this.gamesModule = gamesModule;
	}

	@EventSubscriber
	public void onReadyEvent(ReadyEvent event) {
		gamesModule.init();
	}

}
