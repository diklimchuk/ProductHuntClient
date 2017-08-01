package com.a11eca.producthuntclient.presentation

fun ellipsize(str: String, maxLength: Int): String {
  if (str.length > maxLength) {
    return str.substring(0, maxLength - 3) + "..."
  }
  return str
}