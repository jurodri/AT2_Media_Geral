package com.fatec.at2_media_geral.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fatec.at2_media_geral.ui.screens.CadastroScreen
import com.fatec.at2_media_geral.ui.screens.NotasScreen
import com.fatec.at2_media_geral.ui.screens.ResultadoScreen
import com.fatec.at2_media_geral.ui.state.AlunoState

object Routes {
    const val CADASTRO = "cadastro"
    const val NOTAS = "notas"
    const val RESULTADO = "resultado"
    const val ARG_NOME_ALUNO = "nome_aluno"
    const val ARG_NOTA1 = "nota1"
    const val ARG_NOTA2 = "nota2"
    const val ARG_NOTA3 = "nota3"
    const val NOTAS_COMPLETA = "$NOTAS?$ARG_NOME_ALUNO={$ARG_NOME_ALUNO}"
    const val RESULTADO_COMPLETO = "$RESULTADO/{$ARG_NOME_ALUNO}/{$ARG_NOTA1}/{$ARG_NOTA2}/{$ARG_NOTA3}"

    fun buildNotasRoute(nomeAluno: String? = null): String {
        return if (nomeAluno != null) {
            "$NOTAS?$ARG_NOME_ALUNO=$nomeAluno"
        } else {
            NOTAS
        }
    }

    fun buildResultadoRoute(nomeAluno: String, nota1: Double, nota2: Double, nota3: Double): String {
        return "$RESULTADO/$nomeAluno/$nota1/$nota2/$nota3"
    }
}

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val alunoState = AlunoState()

    NavHost(
        navController = navController,
        startDestination = Routes.CADASTRO
    ) {
        composable(route = Routes.CADASTRO) {
            CadastroScreen(
                alunoState = alunoState,
                onNavigateToNotas = { nome ->
                    navController.navigate(Routes.buildNotasRoute(nome))
                }
            )
        }

        composable(
            route = Routes.NOTAS_COMPLETA,
            arguments = listOf(
                navArgument(Routes.ARG_NOME_ALUNO) {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { backStackEntry ->
            val nomeAluno = backStackEntry.arguments?.getString(Routes.ARG_NOME_ALUNO)

            NotasScreen(
                alunoState = alunoState,
                nomeAluno = nomeAluno,
                onNavigateToResultado = { nota1, nota2, nota3 ->
                    navController.navigate(
                        Routes.buildResultadoRoute(
                            alunoState.nomeAluno,
                            nota1,
                            nota2,
                            nota3
                        )
                    )
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = Routes.RESULTADO_COMPLETO,
            arguments = listOf(
                navArgument(Routes.ARG_NOME_ALUNO) { type = NavType.StringType },
                navArgument(Routes.ARG_NOTA1) { type = NavType.FloatType },
                navArgument(Routes.ARG_NOTA2) { type = NavType.FloatType },
                navArgument(Routes.ARG_NOTA3) { type = NavType.FloatType }
            )
        ) { backStackEntry ->
            val nomeAluno = backStackEntry.arguments?.getString(Routes.ARG_NOME_ALUNO) ?: ""
            val nota1 = backStackEntry.arguments?.getFloat(Routes.ARG_NOTA1)?.toDouble() ?: 0.0
            val nota2 = backStackEntry.arguments?.getFloat(Routes.ARG_NOTA2)?.toDouble() ?: 0.0
            val nota3 = backStackEntry.arguments?.getFloat(Routes.ARG_NOTA3)?.toDouble() ?: 0.0

            ResultadoScreen(
                alunoState = alunoState,
                nomeAluno = nomeAluno,
                nota1 = nota1,
                nota2 = nota2,
                nota3 = nota3,
                onNavigateToCadastro = {
                    navController.popBackStack(Routes.CADASTRO, inclusive = false)
                }
            )
        }
    }
}