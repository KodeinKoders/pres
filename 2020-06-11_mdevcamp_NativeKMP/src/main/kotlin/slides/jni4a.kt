package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.boxShadow
import kotlinx.css.properties.translate
import org.kodein.kpres.PresentationBuilder
import react.dom.br
import styled.*
import ws.utils.opacity
import ws.utils.s
import ws.utils.slideCode
import ws.utils.transform

fun PresentationBuilder.jni4a() = slide(stateCount = 3) { props ->

    styledH1 {
        s {
            css { fontWeight = FontWeight.w200 }
            +"JNI part 4: "
        }
        s {
            +"JVM tests"
        }
    }

    slideCode(props.state, "kotlin", """
        tasks.withType<KotlinJvmTest> {
            systemProperty("java.library.path",
                rootDir.resolve("cpp_jni/build/cmake/out/jni-${'$'}os/lib")
                    .absolutePath)
        }
    """.trimIndent()) {
        opacity(props.state >= 1)
        transform(props.state < 1) { translate(0.px, -2.em) }
    }

    br {}

    slideCode(props.state, "bash", """
        ./gradlew jvmTests
    """.trimIndent()) {
        opacity(props.state >= 2)
        transform(props.state < 2) { translate(0.px, -2.em) }
    }

    br {}

}
