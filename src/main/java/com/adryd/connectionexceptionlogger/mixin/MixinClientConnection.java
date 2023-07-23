package com.adryd.connectionexceptionlogger.mixin;

import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.ClientConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Unique;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class MixinClientConnection {
    @Unique
    private final Logger LOGGER = LoggerFactory.getLogger("ConnectionExceptionLogger");
    @Inject(at = @At("HEAD"), method = "exceptionCaught")
    private void logExceptionStackTrace(ChannelHandlerContext context, Throwable ex, CallbackInfo ci) throws Throwable {
        LOGGER.error("", ex);
    }
}
