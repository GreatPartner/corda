package net.corda.core.identity

import net.corda.core.contracts.PartyAndReference
import net.corda.core.serialization.CordaSerializable
import net.corda.core.utilities.OpaqueBytes
import java.security.PublicKey

@CordaSerializable
class HostedParty(override val host: AbstractParty, owningKey: PublicKey) : AbstractParty(owningKey) {
    override fun nameOrNull(): CordaX500Name? {
        return host.nameOrNull()
    }

    override fun ref(bytes: OpaqueBytes): PartyAndReference {
        return PartyAndReference(this, bytes)
    }
}

fun AbstractParty.asHostFor(publicKey: PublicKey): HostedParty {
    return HostedParty(this, publicKey)
}