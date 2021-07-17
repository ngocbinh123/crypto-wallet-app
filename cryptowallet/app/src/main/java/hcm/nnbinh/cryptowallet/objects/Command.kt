package hcm.nnbinh.cryptowallet.objects

import java.lang.Exception

sealed class Command {
	class ShowError(val e: Exception) : Command()
}