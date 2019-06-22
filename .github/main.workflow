workflow "build" {
  on = "push"
  resolves = [
    "deploy snapshot",
    "mvn clean install",
  ]
}

action "mvn clean install" {
  uses = "gergelyszaz/action-maven-cli@master"
  args = "clean install"
}

action "master only" {
  uses = "actions/bin/filter@712ea355b0921dd7aea27d81e247c48d0db24ee4"
  needs = ["mvn clean install"]
  args = "branch master"
}

action "deploy snapshot" {
  uses = "gergelyszaz/action-maven-cli@master"
  args = "deploy"
  needs = ["master only"]
  secrets = [
    "OSSRH_PASSWORD",
  ]
  env = {
    OSSRH_USERNAME = "gergelyszaz"
  }
}
