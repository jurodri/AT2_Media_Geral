package com.fatec.at2_media_geral.model

class Aluno(
    var nome: String = "",
    val notas: MutableList<Double> = mutableListOf()
) {
    fun adicionarNota(nota: Double) {
        if (notas.size < 3) {
            notas.add(nota)
        }
    }

    fun calcularMedia(): Double {
        return if (notas.isNotEmpty()) notas.average() else 0.0
    }

    fun obterStatus(): String {
        val media = calcularMedia()
        return when {
            media < 6.0 -> "Reprovado"
            media > 9.0 -> "Ótimo Aproveitamento"
            else -> "Aprovado"
        }
    }

    fun todasNotasInseridas(): Boolean = notas.size == 3
}