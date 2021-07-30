package hcm.nnbinh.cryptowallet.objects


sealed class Command {
	class ShowError(val t: Throwable) : Command()
	class ShowHideLoadingBar(val isShow: Boolean = true) : Command()
}