package hcm.nnbinh.cryptowallet.objects

sealed class ProcessState<out R> {
  object NotStart : ProcessState<Nothing>()
  object Loading : ProcessState<Nothing>()
  class Processing : ProcessState<Nothing>()
  data class Success<T>(val data: T?) : ProcessState<T>()
  data class Fail(val e: Throwable) : ProcessState<Nothing>()
}
