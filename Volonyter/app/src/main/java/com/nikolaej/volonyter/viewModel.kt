package com.nikolaej.volonyter

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class viewModel : ViewModel() {

    var vid by mutableStateOf("")
        internal set

    var name by mutableStateOf("")
        internal set
}