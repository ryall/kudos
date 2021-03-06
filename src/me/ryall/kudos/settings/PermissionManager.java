package me.ryall.kudos.settings;

import me.ryall.kudos.Kudos;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.nijikokun.bukkit.Permissions.Permissions;
import com.nijiko.permissions.PermissionHandler;

public class PermissionManager
{
    public static String PERMISSIONS_PREFIX = "kudos.";

    public PermissionHandler permissions;

    public void load()
    {
        if (permissions == null)
        {
            Plugin plugin = Kudos.get().getServer().getPluginManager().getPlugin("Permissions");

            if (plugin != null)
            {
                Kudos.get().getCommunicationManager().logInfo("Attached to Permissions");
                permissions = ((Permissions)plugin).getHandler();
            }
        }
    }

    protected boolean hasGlobalPermission(Player _player)
    {
        return (permissions == null && _player.isOp()) || hasPermission(_player, PERMISSIONS_PREFIX + "*") || hasPermission(_player, "*");
    }

    public boolean hasSetPermission(Player _player)
    {
        return hasGlobalPermission(_player) || 
            hasPermission(_player, PERMISSIONS_PREFIX + "set");
    }
    
    public boolean hasReputationPermission(Player _player)
    {
        return hasGlobalPermission(_player) || 
            hasPermission(_player, PERMISSIONS_PREFIX + "reputation");
    }
    
    public boolean hasRankPermission(Player _player)
    {
        return hasGlobalPermission(_player) || 
            hasPermission(_player, PERMISSIONS_PREFIX + "rank");
    }
    
    public boolean hasTopPermission(Player _player)
    {
        return hasGlobalPermission(_player) || 
            hasPermission(_player, PERMISSIONS_PREFIX + "top");
    }

    public boolean hasLikePermission(Player _player)
    {
        return hasGlobalPermission(_player) || 
            hasPermission(_player, PERMISSIONS_PREFIX + "like");
    }
    
    public boolean hasNeutralPermission(Player _player)
    {
        return hasGlobalPermission(_player) || 
            hasPermission(_player, PERMISSIONS_PREFIX + "neutral");
    }
    
    public boolean hasDislikePermission(Player _player)
    {
        return hasGlobalPermission(_player) || 
            hasPermission(_player, PERMISSIONS_PREFIX + "dislike");
    }

    private boolean hasPermission(Player _player, String _permission)
    {
        if (permissions != null)
            return permissions.has(_player, _permission);

        return false;
    }
}
