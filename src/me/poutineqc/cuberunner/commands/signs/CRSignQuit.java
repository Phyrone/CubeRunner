package me.poutineqc.cuberunner.commands.signs;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.block.SignChangeEvent;

import me.poutineqc.cuberunner.Language;
import me.poutineqc.cuberunner.Language.Messages;
import me.poutineqc.cuberunner.commands.CRCommand;
import me.poutineqc.cuberunner.utils.Permissions;

public class CRSignQuit extends CRSign {

	public CRSignQuit(SignChangeEvent event) {
		super(event.getBlock().getLocation(), SignType.QUIT);

		Language local = Language.getDefault();
		event.setLine(0, "");
		event.setLine(1, ChatColor.translateAlternateColorCodes('&', local.get(Messages.PREFIX_LONG)));
		event.setLine(2, ChatColor.translateAlternateColorCodes('&', local.get(Messages.KEYWORD_SIGN_QUIT)));
		event.setLine(3, "");

		signs.add(this);

	}

	public CRSignQuit(UUID uuid, Location location) {
		super(uuid, location, SignType.QUIT);

		boolean delete = false;
		BlockState block = null;
		try {
			block = location.getBlock().getState();
			if (!(block instanceof Sign))
				delete = true;
		} catch (NullPointerException e) {
			delete = true;
		}

		if (delete) {
			removeSign();
			return;
		}

		signs.add(this);
	}

	@Override
	protected boolean updateSign(Language local, Sign sign) {
		sign.setLine(1, ChatColor.translateAlternateColorCodes('&', local.get(Messages.PREFIX_LONG)));
		sign.setLine(2, ChatColor.translateAlternateColorCodes('&', local.get(Messages.KEYWORD_SIGN_QUIT)));
		sign.update();

		return true;
	}

	@Override
	public void onInteract(Player player) {
		if (Permissions.hasPermission(CRCommand.QUIT.getPermission(), player, true))
			CRCommand.QUIT.execute(plugin, player, new String[0]);
	}

}