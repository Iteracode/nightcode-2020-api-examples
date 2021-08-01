#lang racket

(require racket/list
         racket/match
	 web-server/http
         web-server/servlet-env
         web-server/dispatch
         web-server/http/bindings
	 json)

(define (response
	 #:code    [code/kw 200]
	 #:message [message/kw "OK"]
	 #:seconds [seconds/kw (current-seconds)]
	 #:mime    [mime/kw #f]
	 #:headers [headers/kw empty]
	 #:body    [body/kw empty])
  (define mime
    (cond ((string? mime/kw)
	   (string->bytes/utf-8 mime/kw))
          ((bytes? mime/kw)
           mime/kw)
	  (else
	   #f)))
  (define message

    (cond ((bytes? message/kw)
	   message/kw)
	  ((string? message/kw)
	   (string->bytes/utf-8 message/kw))
          (else
           #f)))
  (define body
    (cond ((string? body/kw)
	   (list (string->bytes/utf-8 body/kw)))
	  ((bytes? body/kw)
	   (list body/kw))
	  ((list? body/kw)
           body/kw)
	  (#t
	   body/kw)))
  (response/full
   code/kw
   message
   seconds/kw
   mime
   headers/kw
   body))

(define (not-found req)
  (response #:code 404
	    #:message "Not Found"))

(define (bad-request)
  (response #:code 400
            #:message "Bad Request"))

(define (index)
  (response #:message "Nightcode"))

(define (hello name)
  (if (non-empty-string? name)
      (response #:mime "application/json"
                #:body (jsexpr->bytes (hash 'message (string-append "hello, " name))))
      (bad-request)))

(define (hello-get req)
  (let ([name (match
                  (bindings-assq #"name" (request-bindings/raw req))
                [(? binding:form? b)
                 (bytes->string/utf-8 (binding:form-value b))]
                [_ "world"])])
    (hello name)))

(define (hello-post req)
  (let ([name (hash-ref
               (bytes->jsexpr (request-post-data/raw req))
               'name
               "")])
    (hello name)))

(define-values (go urls)
  (dispatch-rules
   [("") #:method "get" index]
   [("api" "greetings") #:method "get" hello-get]
   [("api" "greetings") #:method "post" hello-post]
   [else not-found]))

(module+ main
  (serve/servlet
   go
   #:port 6892
   #:command-line? #t
   #:servlet-regexp #rx""))
