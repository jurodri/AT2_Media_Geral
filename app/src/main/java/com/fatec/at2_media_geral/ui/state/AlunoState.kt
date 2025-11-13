package com.fatec.at2_media_geral.ui.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.fatec.at2_media_geral.model.Aluno

class AlunoState {
    var aluno by mutableStateOf(Aluno())
    var nomeAluno by mutableStateOf("")
    var nota1 by mutableStateOf("")
    var nota2 by mutableStateOf("")
    var nota3 by mutableStateOf("")

    fun cadastrarAluno(nome: String) {
        nomeAluno = nome
        aluno = Aluno(nome)
    }

    fun lancarNotas(): Triple<Double, Double, Double>? {
        return try {
            val n1 = nota1.toDouble()
            val n2 = nota2.toDouble()
            val n3 = nota3.toDouble()

            if (n1 in 0.0..10.0 && n2 in 0.0..10.0 && n3 in 0.0..10.0) {
                val novoAluno = Aluno(nomeAluno)
                novoAluno.adicionarNota(n1)
                novoAluno.adicionarNota(n2)
                novoAluno.adicionarNota(n3)
                aluno = novoAluno
                Triple(n1, n2, n3)
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    fun limparDados() {
        aluno = Aluno()
        nomeAluno = ""
        nota1 = ""
        nota2 = ""
        nota3 = ""
    }
}