package com.fady.wordwiz.data.utils


sealed class Resource<out T> {

  data class Error(val error: String?) : Resource<Nothing>()
  data class Success<out T>(val value: T) : Resource<T>()

  class Failure(
      val failureStatus: FailureStatus,
      val code: Int? = null,
      val message: String? = null
  ) : Resource<Nothing>()

  object Loading : Resource<Nothing>()
  object Empty : Resource<Nothing>()

  companion object {
    fun <T> success(data: T): Resource<T> = Success(data)
    fun <T> empty(): Resource<T> = Empty
  }
}
enum class FailureStatus {
    EMPTY,
    API_FAIL,
    NO_INTERNET,
    OTHER
}