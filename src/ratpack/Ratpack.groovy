import static ratpack.groovy.Groovy.ratpack
import ratpack.thymeleaf.ThymeleafModule
import static ratpack.thymeleaf.Template.thymeleafTemplate

ratpack {
  serverConfig {
    findBaseDir('thymeleaf')
  }

  bindings {
    module(ThymeleafModule)
  }

  handlers {

    get {
      render(thymeleafTemplate([
              title:"Hello, Ratpack!",
              header:"Hello, Ratpack!",
              text:"This template got rendered using Thymeleaf",
              users: [[username:'admin'], [username:'demo'], [username:'test']]
              ], "home"))

    } // get

    // Serve assets from 'public'
    files { dir "public" }

  } //handlers
}
