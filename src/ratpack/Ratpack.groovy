import static ratpack.groovy.Groovy.ratpack
import ratpack.thymeleaf.ThymeleafModule
import static ratpack.thymeleaf.Template.thymeleafTemplate

ratpack {
  serverConfig {
    port(3000)
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
              users: [
                [username:'admin', email:'admin@exmaple.com', role:'ADMIN'],
                [username:'demo', email:'demo@exmaple.com', role:'USER'],
                [username:'test', email:'test@exmaple.com', role:'USER']
                ]
              ], "home"))

    } // get

    // Serve assets from 'public'
    files { dir "public" }

  } //handlers
}
