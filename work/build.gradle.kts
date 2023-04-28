plugins {
    id("net.ivoa.vo-dml.vodmltools") version "0.3.14"
    `maven-publish`
    application
}

group = "org.javastro.ivoa.dm"
version = "1.0-SNAPSHOT"

vodml {
    vodslDir.set(file("../model"))
    vodmlDir.set(file("../vo-dml"))
    bindingFiles.setFrom(file("./vodml-binding.xml")
    )

}

dependencies {
    api("org.javastro.ivoa.vo-dml:ivoa-base:1.0-SNAPSHOT") // IMPL using API so that it appears in transitive compile
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}

application {
    mainClass.set("Genschema")
}

tasks.register("UmlToVodml", net.ivoa.vodml.gradle.plugin.XmiTask::class.java) {
    xmiScript.set("xmi2vo-dml_Modelio3.7_UML2.4.1.xsl") // the conversion script
    xmiFile.set(file("../model/dataset_1.0_uml2p4p1.xmi")) //the UML XMI to convert
    vodmlFile.set(file("test.vo-dml.xml")) // the output VO-DML file.
    description = "convert UML to VO-DML"
}
