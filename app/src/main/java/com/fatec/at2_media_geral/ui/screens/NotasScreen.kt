package com.fatec.at2_media_geral.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.fatec.at2_media_geral.ui.state.AlunoState

@Composable
fun NotasScreen(
    alunoState: AlunoState,
    nomeAluno: String?,
    onNavigateToResultado: (Double, Double, Double) -> Unit,
    onNavigateBack: () -> Unit
) {
    val showError = remember { mutableStateOf(false) }

    if (!nomeAluno.isNullOrBlank()) {
        alunoState.nomeAluno = nomeAluno
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Lançamento de Notas",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Text(
                text = "Aluno: ",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = alunoState.nomeAluno,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = alunoState.nota1,
            onValueChange = { alunoState.nota1 = it },
            label = { Text("TP1") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = alunoState.nota2,
            onValueChange = { alunoState.nota2 = it },
            label = { Text("TP2") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = alunoState.nota3,
            onValueChange = { alunoState.nota3 = it },
            label = { Text("TP3") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )

        if (showError.value) {
            Text(
                text = "Insira todas as notas válidas (0-10)",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = onNavigateBack) {
                Text("Voltar")
            }

            Button(
                onClick = {
                    val notas = alunoState.lancarNotas()
                    if (notas != null) {
                        showError.value = false
                        onNavigateToResultado(notas.first, notas.second, notas.third)
                    } else {
                        showError.value = true
                    }
                }
            ) {
                Text("Calcular Média")
            }
        }
    }
}