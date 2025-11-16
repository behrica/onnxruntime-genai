(ns inference
  (:import [ai.onnxruntime.genai
            Model
            Generator
            Tokenizer
            GeneratorParams]))

;(def model (Model. "/workspaces/onnxruntime-genai/test/test_models/hf-internal-testing/tiny-random-gpt2-fp32"))
(def model (Model. "/mnt/extHDD/hf-models/smartvest-llc/gemma-3-1b-it-genai"))

(def tokenizer (Tokenizer. model))
(def generator-params (GeneratorParams. model))
(def sequences (.encode tokenizer "What is AI ?"))
(def generator (Generator. model generator-params))

(.appendTokenSequences generator sequences)
(def next-token-iterator (.iterator generator))
(loop []
  (when (.hasNext next-token-iterator)
    (println (.decode tokenizer (int-array (.next next-token-iterator))) " ")

    (recur)
    )
  )


()
