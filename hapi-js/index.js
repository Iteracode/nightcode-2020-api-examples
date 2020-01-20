"use strict";

const Hapi = require("@hapi/hapi");

function greetingsHandler(h, name = "world") {
  return name.length > 0
    ? { message: `hello, ${name}` }
    : h.response({ error: "Name should not be empty" }).code(400);
}

const init = async () => {
  const server = Hapi.server({
    port: 3000,
    host: "localhost"
  });

  server.route({
    method: "GET",
    path: "/",
    handler: (request, h) => {
      return "Nightcode";
    }
  });
  server.route({
    method: "GET",
    path: "/api/greetings",
    handler: (request, h) => {
      return greetingsHandler(h, request.query.name);
    }
  });

  server.route({
    method: "POST",
    path: "/api/greetings",
    handler: (request, h) => {
      return greetingsHandler(h, request.payload.name);
    }
  });

  await server.start();
  console.log("Server running on %s", server.info.uri);
};

process.on("unhandledRejection", err => {
  console.log(err);
  process.exit(1);
});

init();
