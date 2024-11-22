package pl.lodz.p.edu.rest.repository;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.ValidationAction;
import com.mongodb.client.model.ValidationOptions;
import org.bson.Document;
import org.bson.UuidRepresentation;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.Conventions;
import org.bson.codecs.pojo.PojoCodecProvider;
import pl.lodz.p.edu.rest.model.item.Comics;
import pl.lodz.p.edu.rest.model.item.Movie;
import pl.lodz.p.edu.rest.model.item.Music;
import pl.lodz.p.edu.rest.model.user.Admin;
import pl.lodz.p.edu.rest.model.user.Client;
import pl.lodz.p.edu.rest.model.user.Manager;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMongoEntity implements AutoCloseable  {
    private ConnectionString connectionString = new ConnectionString(
            "mongodb://mongodb1:27017,mongodb2:27018,mongodb3:27019/?replicaSet=replica_set_single");
    private MongoCredential credential = MongoCredential.createCredential(
            "admin",
            "admin",
            "adminpassword".toCharArray()
    );

    private CodecRegistry codecRegistry = CodecRegistries.fromProviders(
            PojoCodecProvider.builder()
                    .automatic(true)
                    .conventions(List.of(Conventions.ANNOTATION_CONVENTION))
                    .register(Client.class, Admin.class, Manager.class)
                    .register(Movie.class, Music.class, Comics.class)
                    .build()
    );

    protected MongoClient mongoClient;
    protected MongoDatabase database;

    protected void initDbConnection() {
        MongoClientSettings settings = MongoClientSettings.builder()
                .credential(credential)
                .applyConnectionString(connectionString)
                .uuidRepresentation(UuidRepresentation.STANDARD)
                .codecRegistry(CodecRegistries.fromRegistries(
                        MongoClientSettings.getDefaultCodecRegistry(),
                        codecRegistry
                ))
                .build();
        mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase("mediastore");
        if (!database.listCollectionNames().into(new ArrayList<>()).contains("users")) {
            createUserCollection();
        }
        if (!database.listCollectionNames().into(new ArrayList<>()).contains("items")) {
            createItemCollection();
        }
        if (!database.listCollectionNames().into(new ArrayList<>()).contains("rents")) {
            createRentsCollection();
        }
    }

    private void createUserCollection() {
        ValidationOptions validationOptions = new ValidationOptions()
                .validator(
                        Document.parse("""
                                {
                                $jsonSchema: {
                                    bsonType: "object",
                                    required: ["login", "password", "firstName", "lastName", "active", "role"],
                                    properties: {
                                        login: {
                                            bsonType: "string",
                                            description: "must be a string and is required"
                                        },
                                        password: {
                                            bsonType: "string",
                                            description: "must be a string and is required"
                                        },
                                        firstName: {
                                            bsonType: "string",
                                            description: "must be a string and is required"
                                        },
                                        lastName: {
                                            bsonType: "string",
                                            description: "must be a string and is required"
                                        },
                                        active: {
                                            bsonType: "bool",
                                            description: "must be a boolean and is required"
                                        },
                                        role: {
                                            bsonType: "string",
                                            description: "must be a string and is required"
                                        }
                                    }
                                }
                                }
                                """)
                ).validationAction(ValidationAction.ERROR);
        CreateCollectionOptions createCollectionOptions = new CreateCollectionOptions()
                .validationOptions(validationOptions);
        database.createCollection("users", createCollectionOptions);

        database.getCollection("users").createIndex(
                new Document("login", 1),
                new IndexOptions().unique(true)
        );
    }

    private void createItemCollection() {
        ValidationOptions validationOptions = new ValidationOptions().validator(
                Document.parse("""
            {
                "$jsonSchema": {
                    "bsonType": "object",
                    "required": ["basePrice", "itemName", "itemType", "available"],
                    "properties": {
                        "basePrice": {
                            "bsonType": "int",
                            "description": "must be an int and is required"
                        },
                        "itemName": {
                            "bsonType": "string",
                            "description": "must be a string and is required"
                        },
                        "itemType": {
                            "bsonType": "string",
                            "description": "must be a string and is required"
                        },
                        "available": {
                            "bsonType": "bool",
                            "description": "must be a bool and is required"
                        },
                        "minutes": {
                            "bsonType": "int",
                            "description": "must be an int"
                        },
                        "casette": {
                            "bsonType": "bool",
                            "description": "must be a bool"
                        },
                        "genre": {
                            "bsonType": "string",
                            "description": "must be a string"
                        },
                        "vinyl": {
                            "bsonType": "bool",
                            "description": "must be a bool"
                        },
                        "pageNumber": {
                            "bsonType": "int",
                            "description": "must be an int"
                        }
                    }
                }
            }
            """)
        ).validationAction(ValidationAction.ERROR);

        CreateCollectionOptions createCollectionOptions = new CreateCollectionOptions()
                .validationOptions(validationOptions);

        database.createCollection("items", createCollectionOptions);
    }


     private void createRentsCollection() {
        ValidationOptions validationOptions = new ValidationOptions().validator(
                Document.parse("""
                        {
                        $jsonSchema: {
                            bsonType: "object",
                            required: ["client", "item", "beginTime", "rentCost"],
                            properties: {
                                "client": {
                                    bsonType: "object",
                                    description: "must be a objectId and is required"
                                },
                                "item": {
                                    bsonType: "object",
                                    description: "must be a objectId and is required"
                                },
                                "beginTime": {
                                    bsonType: "date",
                                    description: "must be a date and is required"
                                },
                                "endTime": {
                                    bsonType: "date",
                                    description: "must be a date"
                                },
                                "rentCost": {
                                    bsonType: "int",
                                    description: "must be a int and is required"
                                },
                                "archive": {
                                    bsonType: "bool",
                                    description: "must be a boolean"
                                }
                            }
                        }
                        }
                        """)
        ).validationAction(ValidationAction.ERROR);
         CreateCollectionOptions createCollectionOptions = new CreateCollectionOptions()
                 .validationOptions(validationOptions);
         database.createCollection("rents", createCollectionOptions);
    }
}