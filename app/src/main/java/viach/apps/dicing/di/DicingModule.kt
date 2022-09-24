package viach.apps.dicing.di

import org.koin.dsl.module
import viach.apps.dicing.dicefactory.RandomDiceFactory
import viach.apps.dicing.fieldcell.FieldCell
import viach.apps.dicing.game.Game
import viach.apps.dicing.game.TwoPlayersGame
import viach.apps.dicing.gamefield.GameField
import viach.apps.dicing.gamefield.SquareNineCellsGameField
import viach.apps.dicing.model.GameType
import viach.apps.dicing.player.FirstPlayer
import viach.apps.dicing.player.SecondPlayer

val dicingModule = module {
    factory<Game>(GameType.USER_VS_AI.qualifier) {
        createTwoPlayersGame(playerPositionMove = FirstPlayer().position)
    }

    factory<Game>(GameType.USER_VS_USER.qualifier) {
        val playerPositionMove = listOf(FirstPlayer().position, SecondPlayer().position).random()
        createTwoPlayersGame(playerPositionMove = playerPositionMove)
    }
}

private fun createTwoPlayersGame(playerPositionMove: Int): TwoPlayersGame =
    TwoPlayersGame(
        diceFactory = RandomDiceFactory,
        initialNextDice = RandomDiceFactory.create(),
        initialFields = listOf(
            SquareNineCellsGameField.newInstance(player = FirstPlayer()),
            SquareNineCellsGameField.newInstance(player = SecondPlayer())
        ),
        playerPositionMove = playerPositionMove
    )