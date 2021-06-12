import com.google.inject.AbstractModule
import com.google.inject.multibindings.Multibinder
import nz.net.ultraq.thymeleaf.LayoutDialect
import org.thymeleaf.dialect.IDialect

import static ratpack.groovy.Groovy.ratpack
import ratpack.thymeleaf.ThymeleafModule
import static ratpack.thymeleaf.Template.thymeleafTemplate

 class ThymeleafLayoutModule extends AbstractModule {
      @Override
      protected void configure() {
         Multibinder.newSetBinder(binder(), IDialect).addBinding().to(LayoutDialect)
      }
 }
ratpack {
  serverConfig {
    port(3000)
    findBaseDir('thymeleaf')
  }

  bindings {
    module(ThymeleafModule)
    module(ThymeleafLayoutModule)
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

    }

  get('about'){
      render thymeleafTemplate([
              title:'About page',
              header: 'About'
      ], 'about')
  }

    // Serve assets from 'public'
    files { dir "public" }

  } //handlers
}
