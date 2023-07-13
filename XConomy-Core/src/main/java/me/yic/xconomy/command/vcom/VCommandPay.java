package me.yic.xconomy.command.vcom;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import me.yic.xconomy.XConomyVelocity;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class VCommandPay implements SimpleCommand {

    @Override
    public void execute(final Invocation invocation) {
        CommandSource source = invocation.source();
        String[] args = invocation.arguments();

        if (source instanceof Player p) {
            if (args.length > 0) {
                String command = "/pay " + String.join(" ", args);
                p.spoofChatInput(command);
            }
            else p.spoofChatInput("/pay");
        }
    }

    @Override
    public CompletableFuture<List<String>> suggestAsync(final Invocation invocation) {
        if (invocation.arguments().length <= 1) {
            List<String> arg = XConomyVelocity.getInstance().server.getAllPlayers().stream().map(Player::getUsername).toList();
            return CompletableFuture.completedFuture(arg);
        }
        if (invocation.arguments().length == 2) {
            return CompletableFuture.completedFuture(List.of("转账金额"));
        }
        return CompletableFuture.completedFuture(List.of());
    }
}