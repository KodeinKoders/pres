package ws.slides

import kotlinx.css.*
import kotlinx.html.classes
import org.kodein.kpres.PresentationBuilder
import org.kodein.kpres.Swipe
import org.kodein.kpres.subSlide
import react.dom.div
import react.dom.h3
import react.dom.li
import react.dom.ul
import styled.css
import styled.styledDiv
import styled.styledH1
import styled.styledP
import ws.charter.kodein
import ws.utils.*

fun PresentationBuilder.actors() = slide(
    stateCount = 10
) { props ->
    titledSlide("Actors & Finite State Machines") {
        subSlide(0..5, props.state, Swipe) {

            slideCode(props.state, "kotlin", """
                class Peer()«s:4-« : CoroutineScope by MainScope()» {«l:1-«
                    private val channel: Channel<Command>()»«l:2-«
                    
                    private suspend fun send(cmd: Command) = channel.send(cmd)»«l:3-«
                    
                    init {
                        launch {«l:5-«
                            channel.consumeEach { command ->
                                execute(command)
                            }»
                        }
                    }«l:5-«

                    private fun suspend execute(cmd: Command) {}
                »»}
            """.trimIndent()) {
                width = 100.pct
            }
        }

        subSlide(6..6, props.state, Swipe) {
            slideCode(props.state, "kotlin", """
                interface SMState {
                    fun process(cmd: Command): Pair<SMState, Action>
                }
            """.trimIndent()) {
                width = 100.pct
            }
        }

        subSlide(7..9, props.state, Swipe) {
            slideCode(props.state - 7, "kotlin", """
                «l:1-«private var currentState: SMState = BeginSMState()
                
                »private suspend fun execute(cmd: Command) {«l:2-«
                    var (nextState, action) = currentState.process(cmd)
                    currentState = nextState
                    execute(action)
                »}«l:2-«

                private suspend fun execute(action: Action) {}»
            """.trimIndent()) {
                width = 100.pct
            }
        }
    }
}
