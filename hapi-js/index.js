"use strict";

const Hapi = require("@hapi/hapi");
const Joi = require("@hapi/joi");

function greetings(name) {
  return { message: `hello, ${name}` };
}

const nameContainerValidator = Joi.object({
  name: Joi.string()
    .optional()
    .default("world")
});

const init = async () => {
  const server = Hapi.server({
    port: 3000,
    host: "localhost"
  });

  server.route({
    method: "GET",
    path: "/",
    handler: () => "Nightcode"
  });
  server.route({
    method: "GET",
    path: "/api/greetings",
    handler: request => greetings(request.query.name),
    options: {
      validate: {
        query: nameContainerValidator
      }
    }
  });

  server.route({
    method: "POST",
    path: "/api/greetings",
    handler: request => greetings(request.payload.name),
    options: {
      validate: {
        payload: nameContainerValidator
      }
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
