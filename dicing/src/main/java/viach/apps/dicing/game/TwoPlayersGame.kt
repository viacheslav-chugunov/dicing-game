package viach.apps.dicing.game

import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import viach.apps.dicing.gamefield.GameField
import viach.apps.dicing.player.Player
import viach.apps.dicing.dice.Dice
import viach.apps.dicing.dicefactory.DiceFactory
import viach.apps.dicing.gamefield.SquareNineCellsGameField
import viach.apps.dicing.player.FirstPlayer
import viach.apps.dicing.player.SecondPlayer

@Parcelize
class TwoPlayersGame(
    private val diceFactory: DiceFactory,
    private val initialNextDice: Dice? = diceFactory.create(),
    override val nextDice: Dice? = initialNextDice,
    private val initialFields: List<GameField> = listOf(
        SquareNineCellsGameField.newInstance(FirstPlayer()),
        SquareNineCellsGameField.newInstance(SecondPlayer())
    ),
    private val fields: List<GameField> = initialFields,
    private val playerPositionMove: Int = fields.map { it.position }.first()
) : Game {

    @IgnoredOnParcel
    override val gameOver: Boolean =
        fields.any { it.gameOver }

    @IgnoredOnParcel
    override val wonPlayerPosition: Int? =
        if (gameOver) fields.maxBy { it.score }.position else null

    override val newGame: Game
        get() = TwoPlayersGame(
            diceFactory = diceFactory,
            nextDice = initialNextDice,
            fields = initialFields,
            playerPositionMove = playerPositionMove
        )

    override fun isPlayerMove(playerPosition: Int): Boolean {
        assertPlayerPosition(playerPosition)
        return playerPositionMove == playerPosition
    }

    override fun getGameField(playerPosition: Int): GameField {
        assertFieldsSize()
        assertPlayerPosition(playerPosition)
        return fields[playerPosition - 1]
    }

    override fun makeMove(fieldPosition: Int, playerPosition: Int): Game {
        assertFieldsSize()
        assertNextDiceNotNull()
        assertPlayerPosition(playerPosition)
        val movingField = fields.first { it.position == playerPosition }
        val otherField = fields.first { it.position != playerPosition }
        val newPlayerPositionMove = fields.map { it.position }.filterNot { it == playerPositionMove }.first()
        val newGameFields = fields
            .toMutableList()
            .apply {
                set(movingField.position - 1, movingField.placeDice(nextDice!!, fieldPosition))
                val newOtherGameField = when (fieldPosition) {
                    in setOf(1, 4, 7) -> {
                        otherField.pullOutDice(1) { this == nextDice }
                            .pullOutDice(4) { this == nextDice }
                            .pullOutDice(7) { this == nextDice }
                    }
                    in setOf(2, 5, 8) -> {
                        otherField.pullOutDice(2) { this == nextDice }
                            .pullOutDice(5) { this == nextDice }
                            .pullOutDice(8) { this == nextDice }
                    }
                    else -> {
                        otherField.pullOutDice(3) { this == nextDice }
                            .pullOutDice(6) { this == nextDice }
                            .pullOutDice(9) { this == nextDice }
                    }
                }
                set(otherField.position - 1, newOtherGameField)
            }
        return TwoPlayersGame(
            diceFactory = diceFactory,
            initialNextDice = initialNextDice,
            nextDice = nextDice,
            initialFields = initialFields,
            fields = newGameFields,
            playerPositionMove = newPlayerPositionMove
        )
    }

    override fun createNextDice(): Game =
        TwoPlayersGame(
            diceFactory = diceFactory,
            initialNextDice = initialNextDice,
            nextDice = diceFactory.create(),
            initialFields = initialFields,
            fields = fields,
            playerPositionMove = playerPositionMove
        )

    private fun assertPlayerPosition(playerPosition: Int) {
        val (firstPosition, secondPosition) = fields.map { it.position }
        if (playerPosition != firstPosition && playerPosition != secondPosition)
            throw IllegalArgumentException("playerPosition = $playerPosition was received, but available only $firstPosition and $secondPosition.")
    }

    private fun assertFieldsSize() {
        if (fields.size != 2)
            throw IllegalStateException("fields.size = ${fields.size} was received, it must be equal 2.")
    }

    private fun assertNextDiceNotNull() {
        if (nextDice == null)
            throw IllegalStateException("nextDice was null, but it must be not null.")
    }
}